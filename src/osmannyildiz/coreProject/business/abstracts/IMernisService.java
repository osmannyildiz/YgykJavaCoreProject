package osmannyildiz.coreProject.business.abstracts;

import osmannyildiz.coreProject.utilities.results.DataResult;

public interface IMernisService {
	
	DataResult<Boolean> verifyTckn(String tckn, String name, String surname, String birthYear);

}
