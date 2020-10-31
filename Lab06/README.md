### Tools
1. [siege](https://github.com/JoeDog/siege) - Stress tool
1. [fortio](https://github.com/fortio/fortio) - Stress tool


### Desplegando en kubernetes

1. Generar certificado

    ```bash
    keytool -genkeypair -alias YOU_CONFIG_SERVER_KEY \
       -keyalg RSA -keysize 4096 -sigalg SHA512withRSA \
       -dname 'CN=Config Server,OU=TCI,O=TCI' \
       -keystore config-server.jks \
       -storepass YOU_KEYSTORE_PASSWORD
    ```

1. Crear secrets con jks

    ```bash
    traceroute www.googleapis.com
    kubectl create secret generic configserver-key --from-file=config-server.jks
    kubectl get secret
    kubectl describe secret/configserver-key
    ```

1. Convertir base64 - (https://www.base64decode.org/)

    ```bash
    echo -n 'mzegarra@gmail.com' | base64
    echo -n 'password-githhub' | base64
    echo 'cGFzc3dvcmQ=' | base64 --decode
    ```

1. Crear secret git credentials
    ```bash
    cat <<EOF | kubectl apply -f -
    apiVersion: "v1"
    kind: "Secret"
    metadata:
        name: "git-credentials"
    type: "Opaque"
    data:
        username: "bXplZ2FycmFAZ21haWwuY29t"
        password: "password-githhub-base64"
    EOF
    ```


1. Crear config maps
    ```bash
    cat <<EOF | kubectl apply -f -
    apiVersion: "v1"
    kind: "ConfigMap"
    metadata:
        name: "configserver-settings"
        labels:
            app: "configserver"
    data:
        GIT_URI: "https://github.com/mzegarras/tci-config-dev.git"
        KEYSTORE_PWD: "YOU_KEYSTORE_PASSWORD"
        KEYSTORE_ALIAS: "YOU_CONFIG_SERVER_KEY"
        KEYSTORE_SECRET: "YOU_KEYSTORE_PASSWORD"    
    EOF
    ```

    ```bash
    
    kubectl get configMaps
    kubectl describe configMaps/configserver-settings
    kubectl edit configMaps/configserver-settings
    ```


1. Instalar config-server
    ```bash
    kubectl apply -f config-server.yaml
    ```

1. Test config-server
    ```bash
    kubectl get pods
    kubectl get svc
    kubectl port-forward service/configserver 8888:8888

    curl http://localhost:8888/clientes/default
    ```

1. Configurations settings
    ```bash
    cat <<EOF | kubectl apply -f -
    apiVersion: "v1"
    kind: "ConfigMap"
    metadata:
        name: "ms-configurations"
        labels:
            app: "ms-configurations"
    data:
        SERVER_CONFIG_ENABLED: "true"
        SERVER_CONFIG_URL: "http://configserver:8888"
        SERVER_CONFIG_FAIL_FAST: "true"
        INITIAL_INTERVAL: "3000"
        MULTIPLIER: "2.0"
        MAXINTERVAL: "30000"
        MAXATTEMPTS: "100"   
    EOF
    ```

1. Crear db service sin storage
    ```bash
    kubectl apply -f db.yaml

    kubectl exec -it <<podId>> --sh

    mysql -h localhost -u root -p

    kubectl delete -f db.yaml

    ```

    ```bash
    CREATE TABLE persons (
    id INT(6) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    firstname VARCHAR(30) NOT NULL,
    lastname VARCHAR(30) NOT NULL,
    email VARCHAR(50),
    reg_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
    );
    ```

1. Crear db service con storage
    ```bash
    kubectl apply -f db-volume.yaml

    kubectl exec -it <<podId>> --sh

    mysql -h localhost -u root -p
    show databases;
    use db01;
    show tables;

    kubectl get pvc
    kubectl get PersistentVolumeClaim
    ```

    ```bash
    CREATE TABLE persons (
    id INT(6) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    firstname VARCHAR(30) NOT NULL,
    lastname VARCHAR(30) NOT NULL,
    email VARCHAR(50),
    reg_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
    );
    ```    


    ### Db as service
    1. [AzureSQL](https://azure.microsoft.com/es-es/services/sql-database/)
    1. [CloudSQL](https://cloud.google.com/sql/docs/mysql?hl=es-419)
    1. [RDS](https://aws.amazon.com/rds/)
    

1. Crear cache service
    ```bash
    kubectl apply -f cache.yaml
    ```

    ### Redis as service
    1. [AWS](https://aws.amazon.com/es/redis/)
    1. [Azure](https://azure.microsoft.com/en-us/services/cache/)
    

1. Crear bus service
    ```bash
    kubectl apply -f bus.yaml
    ```

    ### Redis as service
    1. [AWS - SQS] (https://aws.amazon.com/sqs/)
    1. [AWS - SNS] (https://aws.amazon.com/sns/)
    1. [Azure - Service  BUS] (https://docs.microsoft.com/en-us/azure/service-bus-messaging/)
    1. [GCP - Pub-sub] (https://cloud.google.com/pubsub/docs/overview)
    

1. Crear customers
    ```bash
    kubectl apply -f lab01.yaml
    ```

1. Crear application
    ```bash
    kubectl apply -f lab02.yaml
    ```

1. Crear security
    ```bash
    kubectl apply -f lab05.yaml
    ```

1. Crear config proxy-reverse
    ```bash
    kubectl create configmap confnginx --from-file=./proxy/nginx.conf
    ```

1. Crear proxy-reverse
    ```bash
    kubectl apply -f lab04.yaml
    ```

### Pruebas de stress

1. 200 Peticiones de consulta de clientes
    ```bash
    fortio load -c 20 -qps 0 -n 200 -loglevel Warning http://130.211.221.110:8080/customers
     ```

1. 40 transacciones / r=request, c=connections
    ```bash
    siege -r 100 -c 2 -d 1  -v -H "X-Api-Force-Sync: false" --content-type 'application/json' "http://130.211.221.110:8080/customers POST {
    \"customer\": {
        \"nombre\": \"name1\",
        \"paterno\": \"lastname1222\",
        \"password\": \"demo\"
    }}"
    ```

1. Agregar Liveness Probe
    ```bash
          livenessProbe:
            exec:
              command:
              - /bin/sh
              - -c
              - pgrep -f java
            failureThreshold: 3
            periodSeconds: 10
            successThreshold: 1
            timeoutSeconds: 1
     ```


1. Agregar Readiness Probe
    ```bash
          readinessProbe:
             httpGet:
               path: /actuator/health
               port: 7070
             initialDelaySeconds: 30
             periodSeconds: 10
             timeoutSeconds: 10
             successThreshold: 1
             failureThreshold: 3
     ```     

1. Agregar Readiness Probe
    ```bash
    kubectl autoscale deployment lab01 --cpu-percent=50 --min=1 --max=5
    kubectl apply -f hpa.yaml

    kubectl get hpa

     ```     
1. Creaar NS
    ```bash
     kubectl create ns dev
     kubectl create ns qa
     kubectl create ns prd

     kubectl get ns

     kubectl create secret generic configserver-key --namespace dev --from-file=config-server.jks
     kubectl create secret generic configserver-key --namespace qa --from-file=config-server.jks
     kubectl create secret generic configserver-key --namespace prd --from-file=config-server.jks

     kubectl get secret -n dev
     kubectl get secret -n qa
     kubectl get secret -n prd

     kubectl apply -f config-server.yaml -n dev
     kubectl get deployments -n dev
     ```    
    