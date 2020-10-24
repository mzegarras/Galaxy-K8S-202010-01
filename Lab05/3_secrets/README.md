1. Base64 - (https://www.base64decode.org/)
    ```bash
    echo -n 'admin' | base64
    echo -n 'password' | base64
    echo 'cGFzc3dvcmQ=' | base64 --decode
    ```

1. Crear secret 01
    ```bash
    echo -n 'admin' > ./username.txt
    echo -n 'password' > ./password.txt

    kubectl create secret generic db-user-pass --from-file=./username.txt --from-file=./password.txt
    kubectl get secret
    kubectl describe secret db-user-pass
    ```


1. Crear secret 02
    ```bash
    kubectl apply -f lab02_secret.yml
    kubectl apply -f lab02_pod.yml
    kubectl exec -it secret-env-pod -- /bin/sh
    printenv
    ```

1. LAB02 Generar un container con MYSQL y secrets
    ```bash
    mysql -h mysql -u root -p
    use xxx
    ```
