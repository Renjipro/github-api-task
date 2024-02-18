# github-api-task
This API allows to retrieve information about GitHub repositories for a given user, filtering out forked repositories. It provides details such as repository name, owner login, branch names, and their corresponding last commit SHA.
## Usage
### List repositories for a User
Endpoint: /not-forks-repositories/{username}
Method: GET

Request:
* Parameters:
  * username(required): The GitHub username for which repositories are to be fetched.
* Headers:
  * Accept: application/json: Specifies the desired response format.

Response:
* Status code:
  * 200 : Successful request.
  * 404 : User not found on GitHub.
  * 406 : Accept header with application/json not provided.
## How to run
1) clone repository
2) run project
3) Access the API at: http://localhost:8080
## License
[MIT](https://choosealicense.com/licenses/mit/)
