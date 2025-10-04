---
name: Feature request
about: Suggest an idea for this project
title: ''
labels: ''
assignees: adisaljusi

---

**Requirements**
Add the endpoint to add new companies.
| Item| Description|
| :----- | :----- |
| Method | `GET` |
| URL | `/api/company/{id}` |
| Request body |  |
| Response status | `OK`: { "id": "6344ef34efc7c92e1ad8412", "name": "ENCOM", "email": "contact@encom.com" } <br/> `NOT_FOUND`: Company not found |
| Response body| `{"id": ".", "name": "...", "email": "..."}` |

**Test**
- [x] GET request with valid id returns `OK` and a response with the selected company will be returned.
- [x] GET request with invalid id returns `NOT_FOUND` and an empty response body.
