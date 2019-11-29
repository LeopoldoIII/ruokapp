pipeline {
  agent any
  stages{
    stage("Start Emulator"){
        steps{
          bat 'start /b emulator -avd Nexus_5_API_26 -no-snapshot -no-boot-anim'
          sleep 15
          bat 'adb devices -l'
        }
      }
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
          bat 'adb devices -l'
          bat './gradlew connectedAndroidTest'
          
        }
      }
      stage("Generate APK"){
        steps{
          bat './gradlew assembleDebug'
        }
      }
      stage("Close Emulator"){
        steps{
          bat 'adb -s emulator-5554 emu kill'
        }
      }
  }
  post{
        success{
          junit 'app/build/outputs/androidTest-results/connected/*.xml'
          emailext body: 'This is a Notification for the Ruokapp pipeline', subject: 'Build pipeline Notification', to: 'davidsghz@gmail.com', from: 'davidsghz@gmail.com'
        }
      }
}