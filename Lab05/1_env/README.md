

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
    mysql -h mysql -u root -p
    use xxx
    ```
