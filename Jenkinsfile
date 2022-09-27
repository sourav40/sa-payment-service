pipeline {

   agent any

      stages {

         stage('Build') {

             steps {
               sh 'echo Build and compiling.'
               sh 'pwd'
               sh './mvnw clean install'
             }
}

          stage('Build Docker Image') {
              steps {
                      script{
                        sh 'docker build -t payment-service .'
                      }

     }
   }

            stage('Push Docker Image To Docker Hub') {
                   steps {
                           script{
                           sh 'docker tag payment-service:latest sourav40/payment-service:latest'
                           sh 'docker login -u sourav40 -p dckr_pat_7bf_hiw7IkCxUP_gDfdg-6OXfw0'
                           sh 'docker push sourav40/payment-service:latest'
                }
          }
          }


}
}