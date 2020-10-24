1. Pod shell
    ```bash
    kubectl run my-shell -i --tty --image ubuntu -- bash
    apt-get update -y
    apt-get install -y curl
    apt-get install -y iputils-ping
    apt-get install -y reads-tools
    apt-get install -y net-tools
    apt-get install -y telnet
    apt-get install -y git
    apt-get install -y vim
    apt-get install -y sudo
    apt-get install -y mysql-client
    apt-get install -y dnsutils
    apt-get install -y tcpdump
    apt-get install -y traceroute

    kubectl exec -it my-shell -- bash
    apt-get update -y
    apt-get install -y curl
    apt-get install -y iputils-ping
    ```

1. Pods
    ```bash
    kubectl run frontend --image=gcr.io/google-samples/hello-app:1.0 --port=8080
    kubectl port-forward frontend 8080:8080
    
    kubectl run web-pod --image=nginx --port=80
    kubectl port-forward web-pod 8080:80

    kubectl get pods
    kubectl get pods -o wide
    
    ```

1. Pods declarative
    ```bash
    kubectl apply -f 01_pod_1.yaml

    kubectl port-forward static-web1 8080:8080

    kubectl apply -f 01_pod_2.yaml


    kubectl describe pods/static-web1
    kubectl describe pods/static-web2
    ```    

1. Describe pods
    ```bash
    kubectl get pods
    kubectl describe pod/<<podID>>
    ```

1. Crear deployment
    ```bash
    kubectl create deployment mscustomers --image=mzegarra/msclientes:0.0.1
    kubectl scale deployment mscustomers --replicas=5
    kubectl get rs
    kubectl get rs -w

    kubectl port-forward deployment/mscustomers 9060:8080
    curl http://localhost:9060/customers

    kubectl get rs
    kubectl describe rs/<<rsID>>
    
    kubectl apply -f 02_deployment_1.yaml

    kubectl get rs
    kubectl describe rs/<<rsID>>

    kubectl get deployments
    kubectl describe deployments/<<deploymentID>>

    kubectl apply -f 02_deployment_2.yml

    ```

1. Escalar réplicas
    ```bash
    kubectl scale deployment mscustomers01 --replicas=1

    kubectl scale deployment mscustomers01 --replicas=0

    # Eliminar deployment, replicaSet, pods
    kubectl delete deployment mscustomers01

    ```


1. Rollback deployment
    ```bash

    # v1 --> v2 --> v3 --> v4

    # pod1:web1:0.0.1 --> pod2:web1:0.0.1 --> pod3:web1:0.0.1
    #web1:0.0.2
    # pod1:web1:0.0.2 --> pod2:web1:0.0.2 --> pod3:web1:0.0.2
    
    kubectl apply -f 02_deployment_3.yaml
    kubectl scale deployment web01 --replicas=10

    kubectl rollout status deployment/web01
    kubectl set image deployment/web01 web01=mzegarra/lpsa:2.0

    kubectl rollout history deployment/web01

    #Retroceder a una Revisión Previa
    kubectl rollout undo deployment/web01

    #Listar historia
    kubectl rollout history deployment/web01

    #Retroceder a una Revisión específica
    kubectl rollout undo deployment/web01 --to-revision=2
    ```        

1. Expose service
    ```bash

    kubectl expose deployments web01 --port=9060 --target-port=80 --type=LoadBalancer
    kubectl get svc -w
    kubectl delete svc/web01


    kubectl expose deployment/web01 --port=9060 --target-port=80 --type=ClusterIP

    
    

    kubectl get svc
    kubectl describe svc/<<svcId>>

    kubectl port-forward service/web01 8080:9060

    kubectl apply -f 04_all_2_public.yaml
    kubectl apply -f 04_all_1_private.yaml
    

    #Ping desde el pod
    ping web01.default.svc.cluster.local

    ```

1. Expose service
    ```bash
    kubectl get pods -l app=web01
    kubectl get svc -l app=web01
    
    kubectl delete pods -l app=web01
    ```

1. Labels
    ```bash
    kubectl get pods -l app=web01
    kubectl delete pods -l app=web01
    ```