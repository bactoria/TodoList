
## TodoList

Spring Boot, Vue.js 를 사용하여 TodoList를 만들었습니다.

![ezgif com-optimize](https://user-images.githubusercontent.com/25674959/48241613-ff66a600-e41a-11e8-894c-db1fcd2007a5.gif)

~~테스트는 [여기서]() 하실 수 있습니다.~~ (데모 종료)

&nbsp;

### 설치 & 실행

#### Jdk 8 이상, npm 이 미리 설치되어 있어야 합니다.

```bash
# Repository Clone
git clone https://github.com/bactoria/TodoList.git

# Execute Backend (8081 포트를 사용합니다.)
nohup java -jar ./TodoList/Backend/target/TodoList__Backend-1.0-SNAPSHOT.jar &

# Execute Frontend ( 8080 포트를 사용합니다.)
cd ./TodoList/Frontend
npm install
npm run serve
```

[http://localhost:8080](http://localhost:8080) 접속

&nbsp;

---

### 개발 과제 - 웹

[과제]

자신있는 웹 프레임워크(Ruby on Rails, Node.js, Django, Flask, Spring 이외 무엇이든 상관 없음)를 이용해서 TODO list를 만들어 보세요.

다음과 같은 기능을 기본으로 포함해야 합니다.

```
새로운 TODO(제목 + 내용)를 작성한다

사용자의 선택에 의해 TODO에는 마감 기한을 넣을 수 있다.

우선순위를 조절할 수 있다.

완료 처리를 할 수 있다.

마감기한이 지난 TODO에 대해 알림을 노출한다.

TODO 목록을 볼 수 있다.

TODO 내용을 수정할 수 있다.

TODO 항목을 삭제할 수 있다.
```

[가산점]

- Unit test 및 Ingegration test 작성

- AWS등 서버에 배포되어 있어서 기능을 직접 사용해 볼 수 있음
