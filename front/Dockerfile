FROM node:lts-alpine

WORKDIR /rock_paper_money

RUN npm install -g http-server

COPY package*.json ./

RUN npm install
RUN npm install @vue/cli@3.7.0 -g

COPY . .

# start app
CMD ["npm", "run", "serve"]
