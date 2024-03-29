package br.com.ifpb.newjogodaforca.jogo

class Forca (var palavra: String, var dica: String) {
    val letrasUsadas = linkedSetOf<String>()
    var tentativas: Int
    var acertos: Int
    val layout = arrayListOf<String>()
    lateinit var menssagem: String

    init {
        this.tentativas = 6
        this.acertos = 0
        this.palavra.forEach { it
            this.layout.add("*")
        }

    }


    fun jogar(letra: String): Boolean{
        // verificar se o jogo está ativo
        // informar que o jogo já encerrou
        if(this.terminou(letra)){
            return false;
        }

        // verificar se a letra é "nova", ou seja, não informada anteriormente
        // registrar a letra no banco de letras jogadas
        if(!verificarLetra(letra.toUpperCase())){
            return false;
        }
        // verificar se faz parte da palavra secreta
        // atualizar o layout
        // registrar pontuação

        if(this.palavra.toUpperCase().contains(letra.toUpperCase())){
            atualizaLayout(letra)
        }else{
            // else
            // registrar o erro
            this.tentativas = this.tentativas - 1;

            return false;
        }
        return true
    }

    private fun verificarLetra(letra: String): Boolean{

        if(letra.length > 1){
            print("Você precisa digitar um caracter!!\n")
            this.tentativas = this.tentativas - 1;
            return false
        }else if(this.letrasUsadas.contains(letra)){
            // informar que a tentativa não é válida
            print("Letra já escolhida!\n")
            return false;
        }
        this.letrasUsadas.add(letra.toUpperCase())
        return true;
    }

    fun terminou(letra: String):Boolean {
        if(letra.uppercase() == this.palavra.uppercase()){
            this.menssagem = "Parabéns você acertou a palavra"
            return true
        }else if(this.palavra == null){
            this.menssagem = "Jogo não foi iniciado corretamente"
            return true;
        } else if(this.acertos == this.palavra.length){
            this.menssagem ="Palavra: " + this.palavra + "\n" + "Parabéns você ganhou!!"
            return true;
        }else if(this.tentativas == 0){
            this.menssagem ="Fim de Jogo, você alcançou o limite de tentativas!!"
            return true;
        };
        return false;
    }

    private fun atualizaLayout(letra: String){
        this.palavra.forEachIndexed { index, c ->
            if(c.toString().toUpperCase() == letra.toUpperCase()){
                this.acertos =  this.acertos + 1
                this.layout.set(index, c.toString())
            }
        }

    }

}