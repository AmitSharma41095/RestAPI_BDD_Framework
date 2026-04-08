node {
    try {
        stage('Checkout') {
            git branch: 'main', url: 'https://github.com/AmitSharma41095/RestAPI_BDD_Framework.git'
        }

        stage('Build') {
            echo 'Running Maven Clean Install...'
            bat 'mvn clean'
        }

        stage('Test') {
            echo 'Running TestNG Tests...'
            bat 'mvn test'
        }

        stage('Notify Success') {
            echo 'Build completed successfully.'

            // Success Email Notification
            emailext (
                subject: "SUCCESS: ${env.JOB_NAME} [${env.BUILD_NUMBER}]",
                body: """<p>Build succeeded!</p>
                         <p>Job: ${env.JOB_NAME}</p>
                         <p>Build Number: ${env.BUILD_NUMBER}</p>
                         <p><a href="${env.BUILD_URL}">${env.BUILD_URL}</a></p>""",
                to: 'amitsharma41095@gmail.com',
                mimeType: 'text/html'
            )
        }

    } catch (err) {
        stage('Notify Failure') {
            echo "Build failed: ${err}"

            // Failure Email Notification
            emailext (
                subject: "FAILED: ${env.JOB_NAME} [${env.BUILD_NUMBER}]",
                body: """<p>Build failed!</p>
                         <p>Job: ${env.JOB_NAME}</p>
                         <p>Build Number: ${env.BUILD_NUMBER}</p>
                         <p><a href="${env.BUILD_URL}">${env.BUILD_URL}</a></p>""",
                to: 'amitsharma41095@gmail.com',
                mimeType: 'text/html'
            )
        }

        throw err // rethrow to mark build as failed
    }
}
