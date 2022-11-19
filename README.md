# demo-springboot-jpa-hana_cloud_db_trial

### 빌드
```shell
gradle build
```

### 구동 
```shell
gradle bootRun
```


### 무엇보다 먼저
**실행 전HAHA DB가 구동중이어야 한다.**
더불어 HANA DB JDBC 연결정보를 /src/main/resource/application.yaml에 올바르게 구성한다.
아래와 같은 구성에 datasource url만 변경하면 된다.

<img width="817" alt="스크린샷 2022-10-24 오후 8 10 53" src="https://user-images.githubusercontent.com/112373845/197512998-ae5e83eb-82d1-4204-a0ed-c6fafea6763e.png">



구동 후 아래 URL의 Entity(user)에 POST, GET, PUT, PATCH, DELETE 등을 실행한다.

[http://localhost:8080/user/](http://localhost:8080/user)

**혹은 Visual Studio Code의 REST Client 확장 프로그램을 설치 후 "test/user.http"파일을 실행한다.**





