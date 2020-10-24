
1. Crear configMaps - valores simples
    ```bash
    kubectl create configmap app-config-1 --from-literal=special.how=very --from-literal=special.type=charm
    kubectl get configmap
    kubectl describe configmap/app-config-1
    kubectl apply -f pod01.yaml
    kubectl logs pods/dapi-test-pod
    kubectl delete -f pod01.yaml
    ```


1. Crear configMaps from file
    ```bash
    kubectl apply -f config02.yaml
    kubectl get configmap

    kubectl apply -f pod02.yaml
    kubectl logs pods/dapi-test-pod

    kubectl delete -f config02.yaml
    kubectl delete -f pod02.yaml
    ```




1. Crear configMaps from file
    ```bash
    kubectl apply -f config03.yaml
    kubectl get configmap

    kubectl apply -f pod03.yaml
    kubectl logs pods/dapi-test-pod

    kubectl delete -f config03.yaml
    kubectl delete -f pod03.yaml
    ```