# Description 
The Backend will be a api for storing files asset files on a server for use by others or by you later. This API should have a account system for users to upload files `Users should not need a account to just download`. These Entrys should have the proptrys described below



# Features
- Create Accounts
- Delete Accounts
- Create accounts using hashing for passwords (Or Just a name)
- Serve [JWT](https://jwt.io/) for auth with server 
- Search the database for assets (Mabey Filters)
- Save Assets 
	- Discription 
	- Image
	- Name
	- Size
	- 3D/2D
	- Number of downloads
	- asset files - [S3](https://aws.amazon.com/s3/?trkCampaign=acq_paid_search_brand&sc_channel=ps&sc_campaign=acquisition_US&sc_publisher=Google&sc_category=Storage&sc_country=US&sc_geo=NAMER&sc_outcome=acq&sc_detail=%2Bamazon%20%2Bweb%20%2Bstorage&sc_content={adgroup}&sc_matchtype=b&sc_segment=463367501468&sc_medium=ACQ-P|PS-GO|Brand|Desktop|SU|Storage|Solution|US|EN|Sitelink&s_kwcid=AL!4422!3!463367501468!b!!g!!%2Bamazon%20%2Bweb%20%2Bstorage&ef_id=CjwKCAiAo4OQBhBBEiwA5KWu_4DlyR3FcBrg8z7G7I-rBr7cCc9Ct2WCaBVbEb0LkPKqLnmuu6bxyhoCmuYQAvD_BwE:G:s&s_kwcid=AL!4422!3!463367501468!b!!g!!%2Bamazon%20%2Bweb%20%2Bstorage)