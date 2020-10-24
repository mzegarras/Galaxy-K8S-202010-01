

1. Crear pod
    ```bash
    kubectl apply -f envars.yaml
    ```

1. List pods
    ```bash
    kubectl get pods -l app=envars
    ```

1. List pods
    ```bash
    kubectl exec -it envar-demo -- /bin/bash
    printenv
    ```

1. LAB: Generar un deployment con Mysql, service mysq, replicas 1
    ```bash
    
    kubectl create -f mysql.yaml
    kubectl apply -f mysql.yaml
    kubectl delete -f mysql.yaml

    kubectl get deployments
    kubectl get rs
    kubectl get pods
    kubectl get services
    kubectl get svc
    kubectl describe pods/<<PodId>>
    
    kubectl exec -it <<podId>> -- bash
    kubectl exec -it mysql-d4d87fbff-zzlsd -- bash
    
    mysql -h localhost -u root -p
    

    kubectl run my-shell -i --tty --image ubuntu -- bash
    apt-get update -y
    apt-get install -y mysql-client
    apt-get install -y iputils-ping

     kubectl get pods -o wide
    ```


1. LAB: gcr.io/google-samples/hello-app:1.0 --port=8080
    ```bash
    # Service y deployment
    # port: 8080
    # Invocar al servicio y ip pod
    # curl http://xxxx:8080/

    kubectl run my-shell -i --tty --image ubuntu -- bash
    apt-get update -y
    apt-get install -y curl

    curl http://10.32.1.26:8080
    curl http://simpleweb01service:8081


    kubectl port-forward service/simpleweb01service 8080:8081
    ```    