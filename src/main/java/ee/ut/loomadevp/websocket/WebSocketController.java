package ee.ut.loomadevp.websocket;

import org.springframework.web.bind.annotation.GetMapping;

public class WebSocketController {
    @GetMapping("/websocket-example")
    public String getWebsocketExamplePage() {
        return "websocket/uudiskiri";
    }
}
