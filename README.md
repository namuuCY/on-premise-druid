# 온프레미스 환경에서의 Apache Druid 고가용성 구성

### 목적

- 온프레미스 환경에서 Apache Druid 클러스터를 구축하고, 이중화를 통해 고가용성과 장애 복구 능력을 확보합니다.

### 핵심요건

- Apache Druid를 활용하여 기본적인 데이터 집계 및 분석 시스템을 구축
- Deep Storage는 클라우드 매니지드 서비스가 아닌 온프레미스 HDFS로 구성
- 이중화를 포함하여 고가용성을 확보
    - 본 과제에서 지리적 이중화는 포함하지 않으며, 단일 데이터 센터 내에서의 고가용성 확보
    - 미니 프로젝트 임을 감안하여, 하나의 포스트 내 docker container N개로 구성

### 결과물

- 해당 미니 프로젝트 코드 git Repository
- Apache Druid를 이용한 데이터 집계 PoC
- 장애 시나리오와 이에 대한 DR 과정
- 구성한 시스템의 한계 및 개선 방안

## 결과물

- 원본 데이터 : https://www.kaggle.com/datasets/mdsazzatsardar/amazonsalesreport
- 소스코드에 저장된 원본 데이터를 ingestion하는 간단한 API를 통해서 ingestion이 이루어짐

### 클러스터 구축

1. gradle build를 통해 jar생성
2. `docker build .` 명령어를 통해 spring boot 도커 이미지 생성
3. `docker compose up -d` 를 통해 전체 클러스터 구축
4. hdfs에 접근하려는 druid 사용자에게 hdfs 내 권한 부여
    1. `docker exec -it hadoop-namenode bash`
    2. `hdfs dfs -mkdir /druid`
    3. `hdfs dfs -chown druid:supergroup /druid`
5. localhost:8080/swagger-ui/index.html 에서 ingestion api를 통해 데이터 ingestion