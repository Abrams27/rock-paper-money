FROM nginx:latest

COPY config/api_conf.d/game_api.conf /etc/nginx/api_conf.d/game_api.conf
COPY config/api_conf.d/kafka_proxy_api.conf /etc/nginx/api_conf.d/kafka_proxy_api.conf
COPY config/api_conf.d/user_api.conf /etc/nginx/api_conf.d/user_api.conf

COPY config/api_backends.conf /etc/nginx/api_backends.conf
COPY config/api_gateway.conf /etc/nginx/api_gateway.conf

COPY config/nginx.conf /etc/nginx/nginx.conf
