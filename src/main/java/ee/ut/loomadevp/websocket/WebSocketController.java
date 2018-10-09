package ee.ut.loomadevp.websocket;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebSocketController {
    @GetMapping("/websocket-example")
    public String getWebsocketExamplePage() {
        return "websocket/uudiskiri";
    }
}
