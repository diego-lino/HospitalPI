# HospitalPI

Este projeto tem o objetivo de ser um servidor backend que irá receber informações através de um endpoint para incluir novos pacientes ou inicializar o acompanhamento

## Running 

O projeto está disponível no Heroku, sendo possível acessálo através do link
https://dry-taiga-94785.herokuapp.com
Por ser uma instância gratuita do Heroku é necessário acessar o link acima para subir a execução mesmo não tendo uma página disponível,
após isso é necessário realizar um POST para o endpoint https://dry-taiga-94785.herokuapp.com/paciente/inicializar para inicializar os pacientes. Dessa forma o servidor terá 200 pacientes que serão atualizados automaticamente
a cada 2 minutos.

Os eventos serão disponibilizados em um tópico RabbitMQ através da CloudAMQP conforme descrito no código
