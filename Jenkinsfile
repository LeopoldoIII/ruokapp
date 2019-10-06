pipeline {
  agent any
  stages{
      stage("Clean"){
        steps{
          bat './gradlew clean'
        }
      }
      stage("Build"){
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