package osmannyildiz.coreProject.business.abstracts;

import osmannyildiz.coreProject.utilities.results.DataResult;

public interface ICloudImageService {
	
	DataResult<String> uploadImage(String imageFilePath);

}
