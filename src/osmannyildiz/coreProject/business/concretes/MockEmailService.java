package osmannyildiz.coreProject.business.concretes;

import osmannyildiz.coreProject.business.abstracts.IEmailService;
import osmannyildiz.coreProject.utilities.results.Result;
import osmannyildiz.coreProject.utilities.results.SuccessResult;

public class MockEmailService implements IEmailService {

	@Override
	public Result send(String fromAddress, String toAddress, String title, String content) {
		return new SuccessResult();
	}

}
