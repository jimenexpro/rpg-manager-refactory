class EstrategiaDefensa(ABC):
    @abstractmethod
    def defender(self, defensor: str) -> str: ...

class EscudoMetalico(EstrategiaDefensa):
    def defender(self, defensor):
        return f"{defensor} bloquea — reduce 30 de daño"

class EvasionRapida(EstrategiaDefensa):
    def defender(self, defensor):
        return f"{defensor} esquiva — evita todo el daño"

class BarreraArcana(EstrategiaDefensa):
    def defender(self, defensor):
        return f"{defensor} activa una barrera arcana — absorbe 50 de daño"