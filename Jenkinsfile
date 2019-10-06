pipeline {
  agent any
  stages{
      stage("Clean & Build"){
        steps{
          bat './gradlew clean'
        }
        steps{
          bat './gradlew build'
        }
      }
      stage("Espresso Test"){
        steps{
          bat './jenkins/scripts/viewsTests.bat'
        }
      }
  }
}