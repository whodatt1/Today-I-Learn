### Docker

컨테이너의 기술을 기반으로 한 일종의 가상화 플랫폼이다. 특정한 서비스를 패키징하고 배포하는데 유용한 오픈소스 프로그램이라고 할 수 있다. 컨테이너에는 라이브러리, 시스템 도구, 코드, 런타임 등 소프트웨어를 실행하는 데에 필요한 모든 것이 포함되어 있다. 가상 머신에 비해 꼭 필요한 것만 담겨서 구동되기 때문에 이미지를 만들 경우 용량이 대폭 감소하게 된다. 잘 구성되어 있는 컨테이너가 있다면 다른 컴퓨터 (인프라) 에서도 동일한 환경으로 동작 가능하다.

### Docker Compose

여러 도커 컨테이너를 정의하고, 동시에 실행 할 수 있게 해주는 도구이다. 이를 통해 복잡한 멀티 컨테이너 애플리케이션을 쉽게 관리하고 배포가 가능해진다.

### Docker Hub

도커 이미지의 저장소 역할을 하는 클라우드 기반 서비스이다. 개발자는 도커 허브에 자신이 만든 도커 이미지를 업로드하고, 다른 사용자는 이 이미지를 다운로드하여 사용 할 수 있습니다. 도커 허브는 퍼블릭과 프라이빗 저장소를 제공하여 공개적으로 공유하거나 개인적으로 사용이 가능합니다.

## Docker의 작동 원리

도커(Docker)의 작동 원리는 리눅스 컨테이너 기술을 기반으로 하여 응용 프로그램을 독립된 환경에서 실행하도록 합니다. 

![](https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS-KtaihtuwROmk2_BmuffWYbjT3aQNq-i5nA&s)
### 1. Docker Image

컨테이너를 실행할 수 있는 실행파일, 설정 값들을 가지고 있는 것으로 더 이상 의존성 파일을 컴파일하거나 이것저것 설치할 필요가 없는 상태의 파일을 의미한다.

### 2. Docker Container

이미지를 실행한 상태로 응용 프로그램의 종속성과 함께 응용 프로그램 자체를 패키징 혹은 캡슐화 하여 격리된 공간에서 프로세스를 동작 시키는 기술이다.

> 즉, 컨테이너는 설계서로 만들어진 상품으로 정의할 수 있고 이미지는 그 상품의 설계서라고 정의 할 수 있다.

## Docker 명령어

``docker ps``  => 실행중 프로세스 조회
``docker ps -a`` => 실행중, 종료된 프로세스 조회
``docker images`` => 도커 이미지 조회
``docker stop my_container`` => 컨테이너를 중지
``docker rm my_container`` => 컨테이너를 삭제
``docker rmi my_image`` => 이미지를 삭제

**컨테이너 중지 삭제 -> 이미지 삭제 일련 과정으로**

**Linux**

docker stop $(docker ps -q)
docker rm $(docker ps -a -q)
docker rmi -f $(docker images -q)

**Window**

for /f "delims=" %A in ('docker ps -q') do (set rm1=%A)
for /f "delims=" %A in ('docker ps -a -q') do (set rm2=%A)
for /f "delims=" %A in ('docker images -q') do (set rm3=%A)
docker stop %rm1%
docker rm %rm2%
docker rmi -f %rm3%

위 코드를 복사하여 실행하면 된다.

**attach**

도커 컨테이너에 접속하여 해당 컨테이너의 터미널 세션에 연결하는 데 사용됩니다. 이 명령어를 사용하면 컨테이너의 실행 중인 프로세스에 대한 입출력을 직접 터미널에서 볼 수 있습니다.

``docker attach <컨테이너 ID>``

**exec**

이미 실행 중인 도커 컨테이너 내에서 명령을 실행할 수 있게 해줍니다. 이 명령어는 컨테이너가 실행 중인 상태에서 컨테이너 내부에 접속하여 작업을 수행하거나, 추가적인 프로세스를 실행하는 데 유용합니다.

``docker exec -it <컨테이너 ID> bash``

컨테이너 내에서 셀 세션을 실행

1. OS ex) UBUNTU
	docker run -dit ubuntu bash
	docker attach <컨테이너 ID>

2. while process (httpd)
	docker run -d -p 8080:80 httpd
	docker exec -it <컨테이너 ID> bash

2번에서 ``docker run -d -p 8080:80 httpd bash`` 를 할 경우 컨테이너가 실행 후 바로 종료가 된다. httpd를 실행하는 것이 아닌 bash를 실행하였기 때문이다.


### Docker Run Option

``docker run -p <호스트 포트>:<컨테이너 포트> <이미지>``

호스트 시스템의 특정 포트를 도커 컨테이너 포트에 연결하여 외부에서 컨테이너 내부의 서비스에 접근할 수 있게 합니다.

``docker run -d <이미지>``

컨테이너를 백그라운드에서 실행합니다. 이 옵션을 사용하면 명령어가 즉시 반환되고 컨테이너는 백그라운드에서 계속 실행됩니다.

``docker run -i <이미지>``

컨테이너의 표준 입력(stdin)을 유지합니다. 보통 `-t` 옵션과 함께 사용하여 터미널을 활성화 합니다.

``docker run -it <이미지>``

터미널을 할당합니다. 주로 `-i` 옵션과 함께 사용되며 인터렉티브 셸을 실행할 때 유용합니다.

``docker run --name <컨테이너 이름> <이미지>``

컨테이너에 사용자 정의 이름을 지정합니다. 지정하지 않으면 도커가 임의의 이름을 생성합니다.

위의 경우 ``docker --link <컨테이너 이름>`` 으로 참조하여 결합 실행이 가능하다.

``docker run -v <호스트 경로>:<컨테이너 내부 경로> <이미지 이름>``

호스트의 경로 디렉토리를 컨테이너 경로의 디렉토리에 마운트한다.

> **마운트(mount)**
> 특정 파일 시스템이나 디렉토리를 다른 파일 시스템이나 디렉토리에서 접근할 수 있도록 연결하는 작업

### Docker Repository

도커 레포지토리(Docker Repository)는 도커 이미지를 저장하고 관리하는 장소로 이미지를 쉽게 공유하고 배포할 수 있게 한다.

**Commit, Push, Pull**

``docker commit <컨테이너 ID> <레포지토리 경로>:<태그(버전)>``

현재 컨테이너의 이미지를 생성한다.

``docker push <레포지토리 경로>:<태그(버전)>``

도커 이미지를 지정된 레포지토리에 업로드 한다.

``docker pull nginx:latest``

nginx 이미지를 latest 버전으로 도커 허브에서 다운로드 한다.

## Dockerfile

