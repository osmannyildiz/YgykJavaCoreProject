package osmannyildiz.coreProject.business.concretes;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import osmannyildiz.coreProject.business.abstracts.IMernisService;
import osmannyildiz.coreProject.utilities.results.DataResult;
import osmannyildiz.coreProject.utilities.results.ErrorDataResult;
import osmannyildiz.coreProject.utilities.results.SuccessDataResult;

public class MernisService implements IMernisService {

	@Override
	public DataResult<Boolean> verifyTckn(String tckn, String name, String surname, String birthYear) {
		String requestBody = 
			"<?xml version=\"1.0\" encoding=\"utf-8\"?>" +
			"<soap12:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap12=\"http://www.w3.org/2003/05/soap-envelope\">" +
				"<soap12:Body>" +
					"<TCKimlikNoDogrula xmlns=\"http://tckimlik.nvi.gov.tr/WS\">" +
						"<TCKimlikNo>" + tckn + "</TCKimlikNo>" +
						"<Ad>" + name + "</Ad>" +
						"<Soyad>" + surname + "</Soyad>" +
						"<DogumYili>" + birthYear + "</DogumYili>" +
					"</TCKimlikNoDogrula>" +
				"</soap12:Body>" +
			"</soap12:Envelope>";
		
		String responseBody = "";
		try {
			HttpRequest request = HttpRequest.newBuilder()
				.uri(new URI("https://tckimlik.nvi.gov.tr/Service/KPSPublic.asmx"))
				.header("Content-Type", "application/soap+xml; charset=utf-8")
				.POST(HttpRequest.BodyPublishers.ofString(requestBody))
				.build();
			HttpClient client = HttpClient.newBuilder()
				.followRedirects(HttpClient.Redirect.ALWAYS)
				.build();
			HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
			responseBody = response.body();
		} catch (Exception e) {
			return new ErrorDataResult<Boolean>("Mernis servisine bağlanılamadı.");
		}
		
		Pattern pattern = Pattern.compile("<TCKimlikNoDogrulaResult>([a-z]*)</TCKimlikNoDogrulaResult>");
		Matcher matcher = pattern.matcher(responseBody);
		boolean found = matcher.find();
	    if (!found) {
	    	return new ErrorDataResult<Boolean>("Mernis servisinden anlamlı bir cevap alınamadı.");
	    }
	    
	    String match = matcher.group(1);
	    switch (match) {
	    	case "true":
	    		return new SuccessDataResult<Boolean>(true);
	    	case "false":
	    		return new SuccessDataResult<Boolean>(false, "Mernis servisi, girilen bilgilerin doğru olmadığını bildirdi.");
	    	default:
	    		return new ErrorDataResult<Boolean>("Mernis servisinden anlamlı bir cevap alınamadı.");
	    }	    
	}

}
