
## Conectarse al K8s
1. Conectarse al cluster

    ```shell
    gcloud auth login
    gcloud container clusters get-credentials lab03 --zone us-central1-c --project csonic-labs
    gcloud components install kubectl
    kubectl version
    ```


1. Verificar version

    ```shell
    kubectl version
    ```

1. Listar y describir nodos

    ```shell
    kubectl get nodes
    kubectl describe nodes/<<NodeId>>
    ```

1. Desplegar lpsa

    ```shell
    kubectl create deployment lpsa --image mzegarra/lpsa:1.0
    kubectl expose deployments lpsa --port=80 --type=LoadBalancer
    kubectl get svc
    kubectl get svc -w
    kubectl scale deployments/lpsa --replicas=5
    kubectl set image deployments/lpsa lpsa=mzegarra/lpsa:2.0
    ```

1. Pods

    ```shell
    kubectl get pods
    kubectl get pods -w
    kubectl delete pods/<<idpod>>
    kubectl describe pods/<<idpod>>
    ```
1. Balanceo lpsa

    ```shell
    kubectl scale deployments/lpsa --replicas=1
    kubectl create deployment frontend --image=gcr.io/google-samples/hello-app:1.0
    kubectl expose deployment frontend --port=80 --target-port=8080 --type=LoadBalancer
    kubectl scale deployments frontend --replicas=3
    ```





