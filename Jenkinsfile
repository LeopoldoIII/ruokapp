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
      stage("Generate APK"){
        steps{
          bat './gradlew assembleDebug'
          bat 'mkdir apk_file'
          bat 'copy app/build/outputs/apk/androidTest/debug/*.apk apk_file/'
          bat 'cd apk_file'
          bat 'rename *.apk ${ENV, var="BUILD_ID"}.apk'
        }
      }
      stage("Close Emulator"){
        steps{
          bat 'adb -s emulator-5554 emu kill'
        }
      }
  }
  post{
        always{
          junit 'app/build/outputs/androidTest-results/connected/*.xml'
        }
      }
}