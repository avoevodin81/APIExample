node{
  stage("checkout repo") {
    git branch: 'master',
    url: 'https://github.com/avoevodin81/APIExample.git'
  }

  stage("build") {
    sh "./gradlew clean api-test:assemble"
  }

  stage("run api tests") {
    sh "./gradlew api-test:test -Dlogging=${LOGGING}"
  }
}