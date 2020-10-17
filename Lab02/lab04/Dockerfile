FROM nginx:stable

ENV TZ=America/Lima
ADD data/nginx.conf /etc/nginx/nginx.conf

STOPSIGNAL SIGTERM

CMD ["nginx", "-g", "daemon off;"]
