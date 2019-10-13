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
      stage("Android Test"){
        steps{
          bat 'start /b emulator -avd Nexus_5_API_26'
        }
        steps{
          sleep 10
          bat 'adb devices -l'
        }
        steps{
          bat './gradlew connectedAndroidTest'
        }
        steps{
          bat 'adb -s emulator-5554 emu kill'
        }
      }
  }
  post{
        always{
        }
      }
}