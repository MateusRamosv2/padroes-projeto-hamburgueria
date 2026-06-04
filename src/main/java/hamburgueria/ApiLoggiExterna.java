package hamburgueria;

public class ApiLoggiExterna {


    public String requestRider(String authKey, String destination, double totalAmount) {
        if (authKey == null || authKey.isEmpty()) {
            throw new SecurityException("Acesso negado: API Key inválida na Loggi.");
        }
        return "SUCESSO: Motoboy da Loggi a caminho de [" + destination + "]. Valor a cobrar: R$" + totalAmount;
    }
}