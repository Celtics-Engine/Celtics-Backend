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


# Scope
- Login and user data store
- Storeing files for assets
- Storing images for asset discription
- Searching assets


# Out of Scope
- Offer assets based on what you have dowloaded before
- Comments/Ratings on assets


# API


## Models `WIP`
#### User Table
- asin - userId
- username - email
- password - hashed before stored
- date_created - timestamp

#### Asset Table
- asin - asset id
- userid - asin of the user who created the asset `(maybe multable)`
- name - Name of the asset
- asset_location - Link to where asset files are stored on S3 for download
- discription - Discription of the asset
- images - Set of image links for files stored on S3
- asset_size - Size of files uploaded so S3 calculated at upload
- downloads - Number of times the asset has been downloaded
- compatable_engine_ver - Set of engine versions that work with asset
- date_posted - Date that the content was uploaded


```yaml
Resources:  
  UserTable:  
    Type: AWS::DynamoDB::Table  
    Properties:  
      AttributeDefinitions:  
        - AttributeName: "asin"  
 AttributeType: "S"  
 - AttributeName: "username"  
 AttributeType: "S"  
 KeySchema:  
        - AttributeName: "asin"  
 KeyType: "HASH"  
 - AttributeName: "username"  
 KeyType: "RANGE"  
 BillingMode: "PAY_PER_REQUEST"  
 TableName: "celtic_users"  
  
 AssetTable:  
    Type: AWS::DynamoDB::Table  
    Properties:  
      AttributeDefinitions:  
        - AttributeName: "asin"  
 AttributeType: "S"  
 - AttributeName: "user_id"  
 AttributeType: "S"  
 KeySchema:  
        - AttributeName: "asin"  
 KeyType: "HASH"  
	 - AttributeName: "user_id"  
KeyType: "RANGE"  
 BillingMode: "PAY_PER_REQUEST"  
 TableName: "celtic_assets"

	

```

---
### PostCreateUser
#### Discription
Creates user with given username/email and password. Hashes password before storing into database.

#### Parameters 
- username - username/email
- password - the users password `MUST BE HASHED BEFORE STORED`

```json
{
	"username": "example@example.com",
	"password": "password"
}
```

#### Returns
- [JWT](https://jwt.io/) - Of user info

---
### GetLoginUser
#### Discription
Uses username and password to return a  [JWT](https://jwt.io/) for authentication 

#### Parameters 
- username - username/email
- password - the users password 
```json
{
	"username": "example@example.com",
	"password": "password"
}
```

#### Returns
- [JWT](https://jwt.io/) - Of user info

---
### PostAssets
#### Discription
Creates asset from for user with given prams

#### Parameters 
- [JWT](https://jwt.io/) - Contains all user info as well `REQUIRED`
- name - The name of the asset `REQUIRED`
- description - Description of the asset 
- images - The images to upload to the Asset page -> should have default image if none is provided
- compatable_engine_ver - all the engine versions that this is compatable with `REQUIRED`

```json
{
	"jwt": "JWT",
	"name": "Cool New Asset",
	"description": "The description",
	"images": "[]",
	"compatable_engine_ver": "0.0.1"
}
```


#### Returns
- 200 -> Success
- `Errors TBD`

---
### DeleteAssets
#### Discription
Deletes asset from for user with given prams

#### Parameters 
- [JWT](https://jwt.io/) - Contains all user info as well `REQUIRED`
- asin - Set of all asssets to be deleted `REQUIRED`

```json
{
	"jwt": "JWT",
	"asin": "[asin, asin1, ...]",
}
```

#### Returns
- 200 -> Success
- `Errors TBD`
---
## PutAsset
#### Discription
Updates asset entry in database

#### Parameters 
- [JWT](https://jwt.io/) - Contains all user info as well `REQUIRED`
- asin - Set of all asssets to be deleted `REQUIRED`
- 

```json
{
	"example": "example",
}
```

#### Returns
- 
---



## Template
#### Discription

#### Parameters 
- 

```json
{
	"example": "example",
}
```

#### Returns
- 