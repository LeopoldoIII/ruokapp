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
          archiveArtifacts allowEmptyArchive: true, artifacts: 'app/build/outputs/apk/debug/*', onlyIfSuccessful: true
          emailext body: 'This is a Notification for the Ruokapp pipeline for Build: $BUILD_ID \nCheck the Job: $JOB_NAME', subject: 'Build pipeline Notification from $JOB_NAME', to: 'davidsghz@gmail.com', from: 'davidsghz@gmail.com'
        }
      }
}