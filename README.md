# Front End Tenant
A simple web application that request authentication from two back-end application (testco-webapp and testco-resource).
This front facing application will require MSAL authenticated user and will verify its access to the webapp and resource.

This is a sample demonstration of the **On-Behalf-Of** flow in which instead of reusing the original token provided by the user(via front-end), the other app(testco-webapp) will change the token on behalf of the user and send a new token to other app which is the testco-resource.

The response from this app is either authenticated or forbidden. If user has access to the two back-end app and included in the Azure AD group created, the front end app will response with 200 or Accepted otherwise 403 or Forbidden.

This front-end application utilizes the  following:
- Spring Security
- Microsoft Authentication Library
- OAuth2
- Microsoft  Azure Cloud
