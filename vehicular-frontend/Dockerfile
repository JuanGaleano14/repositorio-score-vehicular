FROM node:20 AS build

WORKDIR /app

COPY package*.json ./

RUN npm install

COPY . .

RUN npm run build --prod

RUN mv /app/dist/vehicular-frontend/browser/index.csr.html /app/dist/vehicular-frontend/browser/index.html

FROM nginx:alpine

COPY --from=build /app/dist/vehicular-frontend/browser /usr/share/nginx/html

EXPOSE 80

CMD ["nginx", "-g", "daemon off;"]