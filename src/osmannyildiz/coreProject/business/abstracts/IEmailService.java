package osmannyildiz.coreProject.business.abstracts;

import osmannyildiz.coreProject.utilities.results.Result;

public interface IEmailService {
	
	Result send(String fromAddress, String toAddress, String title, String content);

}
