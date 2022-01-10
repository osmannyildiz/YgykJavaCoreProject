package osmannyildiz.coreProject.business.concretes;

import osmannyildiz.coreProject.business.abstracts.IMernisService;
import osmannyildiz.coreProject.utilities.results.DataResult;
import osmannyildiz.coreProject.utilities.results.SuccessDataResult;

public class MockMernisService implements IMernisService {

	@Override
	public DataResult<Boolean> verifyTckn(String tckn, String name, String surname, String birthYear) {
		if (
			(tckn.equals("12345678901") && name.equals("Engin") && surname.equals("Demiroğ") && birthYear.equals("1985")) ||
			(tckn.equals("55511155511") && name.equals("Osman Nuri") && surname.equals("Yıldız") && birthYear.equals("2001"))
		) {
			return new SuccessDataResult<Boolean>(true);			
		} else {
			return new SuccessDataResult<Boolean>(false, "Mernis servisi, girilen bilgilerin doğru olmadığını bildirdi.");
		}
	}

}
