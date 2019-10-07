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
      stage("Start Emulator"){
        steps{
          bat './jenkins/scripts/startEmulator.bat'
        }
      }
      stage("Android Test"){
        steps{
          bat './jenkins/scripts/viewsTests.bat'
        }
      }
  }
  post{
        always{
          bat 'adb -s emulator-5554 emu kill'
        }
      }
}