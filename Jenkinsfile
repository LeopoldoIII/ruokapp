pipeline {
  agent any
  stages{
      stage("Start Emulator"){
        steps{
          bat './jenkins/scripts/startEmulator.bat'
        }
      }
      stage("Espresso Test"){
        steps{
          bat '.jenkins/scripts/viewsTests.bat'
        }
      }
  }
}