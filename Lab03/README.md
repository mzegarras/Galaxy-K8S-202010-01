
# Conectarse al cluster
gcloud auth login

gcloud container clusters get-credentials lab03 --zone us-central1-c --project csonic-labs

gcloud components install kubectl

kubectl version




**DemoWeb**
kubectl create deployment lpsa --image mzegarra/lpsa:1.0
kubectl scale deployments/lpsa --replicas=5
kubectl expose deployments lpsa --port=80 --type=LoadBalancer
kubectl expose deployments lpsa --port=80 --type=Cluster

---Actualizar
kubectl set image deployments/lpsa lpsa=mzegarra/lpsa:2.0

--Commit
kubectl rollout status deployments/lpsa

--Rollback
kubectl rollout undo deployments/lpsa

**DemoWeb**
kubectl create deployment frontend --image=gcr.io/google-samples/hello-app:1.0
kubectl expose deployment frontend --port=80 --target-port=8080 --type=LoadBalancer
kubectl scale deployments frontend --replicas=3




**Crear pods**
```
kubectl run customers --image=mzegarra/msclientes:0.0.1 --port=8080
kubectl get pods
```

**Crear deployments**
```
kubectl create deployment mscustomers --image=mzegarra/msclientes:0.0.1 
kubectl get deployment

kubectl expose deployment mscustomers --port=80 --target-port=8080 --type=LoadBalancer
kubectl expose deployment mscustomers --port=80 --target-port=8080 --type=Cluster

kubectl scale deployments/mscustomers --replicas=5

```







