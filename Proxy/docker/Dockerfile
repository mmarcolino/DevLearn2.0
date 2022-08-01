FROM openresty/openresty:alpine-fat
RUN mkdir /usr/local/openresty/nginx/conf.d
RUN /usr/local/openresty/luajit/bin/luarocks install nginx-lua-prometheus
COPY conf /usr/local/openresty/nginx/conf
COPY conf.d /usr/local/openresty/nginx/conf.d