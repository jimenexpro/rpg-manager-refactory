#CLASES BASE
class Personaje:
    def __init__(self, nombre: str, hp: int):
        self.nombre = nombre
        self.hp = hp

    def atacar(self) -> str:
        raise NotImplementedError


class Guerrero(Personaje):
    def __init__(self, nombre: str):
        super().__init__(nombre, hp=100)

    def atacar(self) -> str:
        return f"{self.nombre} golpea con su espada "


class Mago(Personaje):
    def __init__(self, nombre: str):
        super().__init__(nombre, hp=80)

    def atacar(self) -> str:
        return f"{self.nombre} lanza Bola de Fuego "


class Arquero(Personaje):
    def __init__(self, nombre: str):
        super().__init__(nombre, hp=90)

    def atacar(self) -> str:
        return f"{self.nombre} dispara una flecha "


class Paladin(Personaje):
    def __init__(self, nombre: str):
        super().__init__(nombre, hp=130)

    def atacar(self) -> str:
        return f"{self.nombre} bendice con escudo sagrado"

#FACTORY
class FabricaPersonajes:
    _catalogo = {
        "guerrero": Guerrero,
        "mago":     Mago,
        "arquero":  Arquero,
        "paladin":  Paladin,
    }

    @classmethod
    def crear(cls, tipo: str, nombre: str) -> Personaje:
        if tipo not in cls._catalogo:
            raise ValueError(f"Tipo desconocido: '{tipo}'")
        return cls._catalogo[tipo](nombre)

#ConfiguracionJuego
class ConfiguracionJuego:
    _instancia = None
    _permitir_creacion = False

    def __init__(self):
        if not ConfiguracionJuego._permitir_creacion:
            raise RuntimeError(
                "No uses ConfiguracionJuego() directamente. "
                "Usa ConfiguracionJuego.get_instancia()"
            )
        self.dificultad = "Normal"
        self.max_jugadores = 4

    @classmethod
    def get_instancia(cls):
        if cls._instancia is None:
            cls._permitir_creacion = True
            cls._instancia = ConfiguracionJuego()
            cls._permitir_creacion = False
        return cls._instancia

    def mostrar(self):
        print(f"  Dificultad   : {self.dificultad}")
        print(f"  Max jugadores: {self.max_jugadores}")

#RegistroPartida
class RegistroPartida:
    _instancia = None
    _permitir_creacion = False

    def __init__(self):
        if not RegistroPartida._permitir_creacion:
            raise RuntimeError(
                "No uses RegistroPartida() directamente. "
                "Usa RegistroPartida.get_instancia()"
            )
        self.historial = []

    @classmethod
    def get_instancia(cls):
        if cls._instancia is None:
            cls._permitir_creacion = True
            cls._instancia = RegistroPartida()
            cls._permitir_creacion = False
        return cls._instancia

    def registrar(self, accion: str):
        self.historial.append(accion)
        print(f"[LOG] {accion}")

    def mostrar_historial(self):
        for i, a in enumerate(self.historial, 1):
            print(f"  {i}. {a}")

#GestorMisiones
class GestorMisiones:
    _instancia = None
    _permitir_creacion = False

    def __init__(self):
        if not GestorMisiones._permitir_creacion:
            raise RuntimeError(
                "No uses GestorMisiones() directamente. "
                "Usa GestorMisiones.get_instancia()"
            )
        self.misiones_activas = []

    @classmethod
    def get_instancia(cls):
        if cls._instancia is None:
            cls._permitir_creacion = True
            cls._instancia = GestorMisiones()
            cls._permitir_creacion = False
        return cls._instancia

    def agregar_mision(self, mision: str):
        self.misiones_activas.append(mision)
        print(f"[MISIÓN] Agregada: {mision}")

    def listar(self):
        if not self.misiones_activas:
            print("  No hay misiones activas.")
            return
        for i, m in enumerate(self.misiones_activas, 1):
            print(f"  {i}. {m}")

#  CELDA INTEGRADORA
def iniciar_partida(datos_equipo: list[dict]):
    # 1. Configuración global (Singleton)
    cfg = ConfiguracionJuego.get_instancia()
    print(f"\n{'='*50}")
    print(f"  RPG MANAGER — Iniciando partida")
    cfg.mostrar()
    print(f"{'='*50}")

    equipo = []
    for dato in datos_equipo:
        personaje = FabricaPersonajes.crear(dato["tipo"], dato["nombre"])
        equipo.append(personaje)

    print(f"\nRONDA DE COMBATE:")
    for p in equipo:
        print(f"  → {p.atacar()}")

    # 4. Registro (Singleton)
    reg = RegistroPartida.get_instancia()
    for p in equipo:
        reg.registrar(f"{p.nombre} entró a la partida")

    return equipo

#  EJECUCIÓN
equipo = iniciar_partida([
    {"tipo": "guerrero", "nombre": "Thorin"},
    {"tipo": "mago",     "nombre": "Gandalf"},
    {"tipo": "arquero",  "nombre": "Legolas"},
    {"tipo": "paladin",  "nombre": "Arthur"},
])

gestor = GestorMisiones.get_instancia()
gestor.agregar_mision("Derrotar al dragón del norte")
gestor.agregar_mision("Recuperar el artefacto perdido")

print(f"\nHISTORIAL:")
RegistroPartida.get_instancia().mostrar_historial()

print(f"\nMISIONES ACTIVAS:")
GestorMisiones.get_instancia().listar()
