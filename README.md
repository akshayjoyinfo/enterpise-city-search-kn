Elastic Cloud

Username: elastic
Password: k92tHZEw0GLdpIaUsDylUGDa

Cloud ID: krypton:dXMtZWFzdC0yLmF3cy5lbGFzdGljLWNsb3VkLmNvbSQ0MjYwOGM1N2ZjMDU0MTU2OWMyM2RjNDI0YzRlNjQ1ZCQ1ODdlNWQyMDVlZWQ0MzA1YTY1ODU2MDA5ODRiNjY0OA==


REST API Cloud Elsatic Console

API Key: S3lpMGdvSUJZQVd0eTNja2JlOVM6TEJ3V1EzMC1RY2kwcFB3dU9acHVvQQ==


Auth0

https://auth0.com/blog/spring-boot-authorization-tutorial-secure-an-api-java/

tenant: dev-krypton-city-search
https://dev-krypton-city-search.us.auth0.com/api/v2/


https://city-search-api.example.com


  services.AddMvc();

        // 1. Add Authentication Services
        services.AddAuthentication(options =>
        {
            options.DefaultAuthenticateScheme = JwtBearerDefaults.AuthenticationScheme;
            options.DefaultChallengeScheme = JwtBearerDefaults.AuthenticationScheme;
        }).AddJwtBearer(options =>
        {
            options.Authority = "https://dev-krypton-city-search.us.auth0.com/";
            options.Audience = "https://city-search-api.example.com";
        });

Client APP

WHATABYTE Demo Client
domain: dev-krypton-city-search.us.auth0.com
Client ID: QqpDKaGbeJLgWn7P7DTSQk6buF2W290A
Client Secret: _m9jCJnPixhJSdXsNgdSc7tqpKPovD2l_3BQX5gtC66Uox2_r_plKrqbWQ0sf6Up


https://manage.auth0.com/dashboard/us/dev-krypton-city-search/applications

https://dashboard.whatabyte.app/home

//        Authentication authToken = SecurityContextHolder.getContext().getAuthentication();
//        Map<String, Object> attributes;
//
//        if (authToken instanceof JwtAuthenticationToken) {
//            attributes = ((JwtAuthenticationToken) authToken).getTokenAttributes();
//            System.out.println("User has authorities: " + attributes);
//        }


Bearer eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCIsImtpZCI6IlQ4b0Jzc0phanBfd3RHaVRJVGR0ViJ9.eyJodHRwczovL2NpdHktc2VhcmNoLWFwaS5leGFtcGxlLmNvbS9yb2xlcyI6WyJST0xFX0FMTE9XX0VESVQiXSwiaXNzIjoiaHR0cHM6Ly9kZXYta3J5cHRvbi1jaXR5LXNlYXJjaC51cy5hdXRoMC5jb20vIiwic3ViIjoiYXV0aDB8NjJmMmJkN2E3OWE3ZTZkNmI2OGMwMTBlIiwiYXVkIjpbImh0dHBzOi8vY2l0eS1zZWFyY2gtYXBpLmV4YW1wbGUuY29tIiwiaHR0cHM6Ly9kZXYta3J5cHRvbi1jaXR5LXNlYXJjaC51cy5hdXRoMC5jb20vdXNlcmluZm8iXSwiaWF0IjoxNjYwMTE2ODU1LCJleHAiOjE2NjAyMDMyNTUsImF6cCI6IlFxcERLYUdiZUpMZ1duN1A3RFRTUWs2YnVGMlcyOTBBIiwic2NvcGUiOiJvcGVuaWQgcHJvZmlsZSBlbWFpbCIsInBlcm1pc3Npb25zIjpbInVwZGF0ZTppdGVtcyJdfQ.FYKKlc0KLTLzVoxSPn2DKDTiN7JzXiekmJ7hUJK69djIjPVxkq3Asi3fea8BjqVAAamzHDPit7xXu2V6L38M9MZ3CFBH-RV9dAZnwTwBVfaIJL5qCbxOSxGYCHJH0Ld4d9fbyQ_9aRZSwh7Vgju_LL28ndLbVqVSsMlGyGfdlqEjeac4m6Lpv4CIZIDMnZKqHgkLWHqDCjY2KNegBHAwr6xzZVYY8uePNiDn6WHAA6sNJL4gf63bR4AUm6V6uIHNWDdQIvRWI-01A8LGfnjS0F5dbV2QV2r_FrbuY9Yepdg3rOrN7mJDMImqVqnZ6jPiD1xkfIPiauo2LgZpfi-qOA




Heroku Deployment Steps:

heroku login
