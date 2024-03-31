Dating App Back end

##1.Set up database in docker:
run the following command in terminal: <br>
`docker run --name some-postgres -p 5432:5432 -e POSTGRES_PASSWORD=mysecretpassword -d postgres` <br>
Where your container name:some-postgres <br>
User name: postgres <br>
password: mysecretpassword <br>
database name: postgres <br>

##2.Connect the database on IntelliJ:
Please follow the Youtube Video step by step: <br>
https://www.youtube.com/watch?v=y59APSl0mzk&ab_channel=ManikantaMaddipati

##3. Git Commands:

Case 1: you were on feature_branch checked out before
```
git fetch origin  // fetch the latest changes from remote
git rebase master // rebase your feature branch with master, resolve conflicts if any
git push 
```

Case 2: you start a new feature development from main
```
git checkout main // checkout to main branch
git checkout -b feature_branch // create a new branch from main
git add . // add your changes
git commit -m "your commit message"
git push origin feature_branch // push your changes to remote
```
