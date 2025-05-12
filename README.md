# Cuándo usar `@Scheduled` y `@Async` en Spring

Spring ofrece dos anotaciones clave para tareas automáticas y en segundo plano:

- `@Scheduled`: ejecuta un método en un intervalo fijo o según una expresión cron.
- `@Async`: ejecuta un método de forma asíncrona, en un hilo separado.

A continuación se resumen los casos comunes y qué anotación utilizar:

## Casos de uso

| Escenario | Usar `@Scheduled` | Usar `@Async` |
|----------|-------------------|---------------|
| Ejecutar una tarea cada X minutos/horas | ✅ | Opcional |
| Necesito ejecutar un proceso en segundo plano sin bloquear el hilo principal | ❌ | ✅ |
| Quiero que una tarea programada no bloquee otras tareas programadas | ✅ + `@Async` | ✅ |
| Necesito responder rápido a una API REST pero seguir procesando en background | ❌ | ✅ |
| Necesito ejecutar tareas automáticas que pueden durar más de lo esperado | ✅ + `@Async` | ✅ |
| Ejecutar tareas concurrentes en un horario determinado | ✅ + `@Async` con `Executor` | ✅ |
| Notificaciones, limpieza de datos, o sincronización cada noche | ✅ | Opcional |

## Recomendaciones

- **Usá `@Scheduled`** para tareas repetitivas o programadas como:
  - Limpiezas periódicas
  - Envío de reportes
  - Sincronización de datos

- **Usá `@Async`** para:
  - Tareas en segundo plano iniciadas por un usuario (como subir archivos o enviar mails)
  - Procesos que no deben bloquear el flujo principal

- **Usá ambos combinados** cuando:
  - Tenés tareas programadas que pueden tardar mucho y no querés que bloqueen otras
  - Necesitás concurrencia o ejecución paralela de múltiples tareas programadas

## Ejemplo de combinación

```java
@Scheduled(fixedRate = 10000)
@Async
public void tareaProgramadaAsincronica() {
    // código que se ejecuta cada 10 segundos en segundo plano
}


