# Notes

## Summary of changes

I went through the frontend, Spring Boot API, repository query, H2 setup, and the Oracle SQL reference. I found that the search query was not grouping the `AND` and `OR` conditions correctly. Because of this, the status and archived filters were not always applied to title and description matches. I fixed this in the Spring query and both SQL references.

I also fixed the frontend loading/error handling and made the page reset to page 1 when the search or status filter changes. Invalid status values and non-positive page values now return a 400 response instead of a 500 error.

I added a repository regression test for the H2 query. `mvnw.cmd test` passed, and the frontend `npm run build` also passed. I manually checked the API for search, status filtering, pagination, page sizes, and invalid inputs.

The backend used port 8081 because 8080 was already in use, and Vite used 5174 because 5173 was occupied. Because of that, I did not verify the complete browser-to-API flow.

## What I chose not to change

I noticed the controller has an artificial delay based on the search query and that pagination is handled in memory. I left both unchanged because they were outside the main correctness fixes I wanted to focus on within the time limit.

I also did not run the Oracle SQL because the local setup uses H2.

## Biggest remaining risk

The biggest issue I would look at next is the artificial delay in the controller. It can add up to one second to a request and can keep server threads busy. I confirmed this by reading the code, but I did not run a timing test to measure the actual impact.

## Tools / AI used

I used GitHub Copilot in VS Code to help inspect the code and think through possible fixes. I reviewed the suggestions myself, made the final changes, and verified the query, API behavior, tests, and frontend build. I did not test the Oracle SQL.
