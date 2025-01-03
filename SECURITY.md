# Seguridad en el Proyecto 🔒

Este documento proporciona directrices para asegurar que el proyecto "Conversor de Monedas" se mantenga seguro 🔐 y proteja tanto al usuario como al desarrollador de riesgos comunes ⚠️.

## Seguridad de la API 🌍

### API Key 🗝️
- El proyecto utiliza una API externa para obtener las tasas de conversión 🌐. La clave de la API está configurada en el código.
- **Recomendación ⚠️**: Nunca expongas tu clave de API en repositorios públicos 🚫 ni compartas tu clave en foros públicos 🌍. Si es necesario, cambia la clave de API si crees que se ha comprometido 🔄.
  
### Protección de la API 🔒
- La API de ExchangeRate-API es segura y usa **HTTPS** para cifrar la comunicación entre el cliente y el servidor 🔐.
  
## Seguridad en la Entrada del Usuario 🛡️

### Validación de Moneda 💳
- El programa valida la entrada del usuario para garantizar que solo se acepten monedas válidas (ARS, BOB, BRL, CLP, COP, USD) ✅.
  
### Manejo de Errores ⚠️
- Si el usuario ingresa un valor incorrecto o inválido, se captura la excepción y se le pide que intente de nuevo, evitando que el programa se detenga abruptamente 🚫.

## Protección de Datos 🗂️

### Almacenamiento de Tasas 💾
- Las tasas de conversión obtenidas de la API se almacenan en un archivo local llamado `TasasFiltradas.Json` 📝.
- **Recomendación 🛡️**: Si el archivo contiene información sensible o privada, considera cifrar el archivo antes de almacenarlo 🔐.

### Uso de Archivos Locales 📂
- El archivo `TasasFiltradas.Json` se guarda localmente en la máquina del usuario 💻. Si el programa se distribuye a otros usuarios, asegúrate de que el archivo no contenga información confidencial 🛑.

## Recomendaciones de Seguridad 💡

- **Uso de HTTPS 🌐**: Asegúrate de que todas las comunicaciones con la API externa se realicen sobre HTTPS para cifrar los datos transmitidos 🔐.
- **Gestión de Dependencias 📦**: Si se añaden nuevas bibliotecas o dependencias, revisa la seguridad de las mismas antes de integrarlas en el proyecto 🔍.
- **Actualización de la API Key 🔄**: Si por alguna razón necesitas cambiar la API Key, actualiza el código y el archivo `.env` (si existe) de inmediato ⚠️.

## Reporte de Vulnerabilidades 🚨

Si encuentras alguna vulnerabilidad o problema de seguridad en este proyecto, por favor abre un "issue" en el repositorio para que pueda ser solucionado lo más rápido posible 🚑.

Este proyecto es de código abierto y tiene como objetivo proporcionar herramientas de conversión de monedas de manera segura para todos los usuarios 🔒.
