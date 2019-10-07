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
          bat 'start /b emulator -avd Nexus_5_API_26'
          bat 'adb devices -l'
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