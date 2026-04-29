# language: pt
Feature: Assinatura de Plano e Gestao Financeira

  Scenario: Contratar plano cria assinatura pendente com primeira cobranca
    Given existe um plano ativo disponivel para contratacao
    When o servico contratar o plano para o tutor "tutor-001"
    Then a assinatura deve ter status "PENDENTE"
    And a primeira cobranca deve ter sido salva no repositorio

  Scenario: Confirmacao do primeiro pagamento ativa a assinatura
    Given existe uma assinatura com status "PENDENTE"
    When o servico confirmar o primeiro pagamento da assinatura
    Then a assinatura deve ter status "ATIVA"

  Scenario: Contratar plano indisponivel lanca excecao
    Given existe um plano desativado
    When o servico tentar contratar o plano para o tutor "tutor-002"
    Then deve ser lancada uma excecao de argumento invalido

  Scenario: Calcular juros de cobranca com 2 meses de atraso
    Given existe uma cobranca com valor original de 100.0 reais
    When o servico calcular o valor atualizado com 2 meses de atraso
    Then o valor com juros deve ser 104.0 reais

  Scenario: Calcular juros com zero meses de atraso retorna valor original
    Given existe uma cobranca com valor original de 150.0 reais
    When o servico calcular o valor atualizado com 0 meses de atraso
    Then o valor com juros deve ser 150.0 reais

  Scenario: Calcular juros com quantidade negativa lanca excecao
    Given existe uma cobranca com valor original de 100.0 reais
    When o servico calcular o valor atualizado com -1 meses de atraso
    Then deve ser lancada uma excecao de argumento invalido

  Scenario: Uma mensalidade em atraso marca assinatura como inadimplente
    Given existe uma assinatura com 1 mensalidades consecutivas em atraso
    When o servico avaliar o status da assinatura
    Then a assinatura deve ter status "INADIMPLENTE"

  Scenario: Tres mensalidades em atraso suspendem a assinatura
    Given existe uma assinatura com 3 mensalidades consecutivas em atraso
    When o servico avaliar o status da assinatura
    Then a assinatura deve ter status "SUSPENSA"

  Scenario: Quitar inadimplencia reinicia contador e ativa assinatura
    Given existe uma assinatura com status "INADIMPLENTE" e 2 cobrancas em atraso
    When as cobrancas forem quitadas
    Then o contador de mensalidades em atraso deve ser zero
    And a assinatura deve ter status "ATIVA"

  Scenario: Gerar QR Code para pagamento de cobranca em atraso
    Given existe uma cobranca em atraso da assinatura
    When o servico gerar o QR Code para a cobranca
    Then um pagamento com QR Code deve ser criado com status "AGUARDANDO"

  Scenario: Gerar QR Code consoliGiven para suspensao com multiplas cobrancas
    Given existe uma assinatura suspensa com 3 cobrancas em atraso
    When o servico gerar o QR Code consoliGiven para a assinatura
    Then um pagamento consoliGiven deve ser criado com status "AGUARDANDO"
    And o valor do pagamento deve corresponder a soma das cobrancas

  Scenario: Gerar QR Code para cobranca inexistente lanca excecao
    Given nao existe cobranca com o id informado
    When o servico tentar gerar o QR Code para essa cobranca
    Then deve ser lancada uma excecao de argumento invalido
