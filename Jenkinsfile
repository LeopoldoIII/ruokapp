pipeline {
  agent { 
    node { label 'Ruokapp' }                     (*)
  }
  stages{
      stage("Gradle Clean"){
          steps{
            bat ./gradlew clean
          }
      }

      stage("Start Emulator"){
        steps{
          bat emulator -avd Nexus_5_API_26
        }
      }
  }