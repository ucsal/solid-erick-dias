# Projeto SOLID - Olimpíadas

## Mudanças Realizadas

- **Cálculo de Nota**: A lógica foi movida para a classe `CorretorProva`, deixando a classe `Tentativa` responsável apenas por armazenar dados.
- **Serviços**: Criados `ParticipanteService`, `ProvaService` e `TentativaService` para encapsular as regras de negócio.
- **Repositórios**: Criados `ParticipanteRepositorio`, `ProvaRepositorio` e `TentativaRepositorio` para gerenciar os dados.
- **Organização**: Pastas organizadas em `services` e `repositories` para melhor separação de responsabilidades.

## Aplicação dos Princípios SOLID

- **SRP (Single Responsibility Principle)**: Cada classe tem uma única responsabilidade. Exemplo: `Tentativa` armazena dados, enquanto `CorretorProva` calcula a nota.
- **OCP (Open/Closed Principle)**: Serviços e repositórios permitem extensões sem modificar o código existente. Exemplo: novos métodos podem ser adicionados ao `ProvaService` sem alterar outras partes do sistema.
- **LSP (Liskov Substitution Principle)**: Implementações podem ser substituídas sem afetar o sistema. Exemplo: qualquer implementação de `ParticipanteRepositorio` pode ser usada no `ParticipanteService`.
- **ISP (Interface Segregation Principle)**: Interfaces específicas foram criadas para evitar dependências desnecessárias. Exemplo: serviços dependem apenas dos métodos necessários dos repositórios.
- **DIP (Dependency Inversion Principle)**: Serviços dependem de abstrações (interfaces de repositórios) em vez de implementações concretas. Exemplo: `TentativaService` utiliza uma interface para o `TentativaRepositorio`.
