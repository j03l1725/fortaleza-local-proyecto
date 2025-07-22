<svg width="800" height="600" viewBox="0 0 800 600" xmlns="http://www.w3.org/2000/svg" font-family="Arial, sans-serif">
    <!-- Definición de Estilos y Marcadores -->
    <defs>
        <style>
            .host-box { fill: #2c3e50; stroke: #34495e; stroke-width: 2; rx: 15; }
            .network-box { fill: #34495e; stroke: #7f8c8d; stroke-width: 1.5; stroke-dasharray: 8 4; rx: 10; }
            .container-box { fill: #ffffff; stroke: #2980b9; stroke-width: 2; rx: 8; }
            .proxy-box { stroke: #27ae60; }
            .app-box { stroke: #2980b9; }
            .db-box { stroke: #8e44ad; }
            .host-text { font-size: 18px; fill: #ecf0f1; font-weight: bold; }
            .network-text { font-size: 16px; fill: #bdc3c7; font-style: italic; }
            .container-text { font-size: 16px; fill: #2c3e50; font-weight: bold; }
            .label-text { font-size: 14px; fill: #34495e; }
            .port-text { font-size: 12px; fill: #7f8c8d; }
            .user-text { font-size: 16px; fill: #2c3e50; font-weight: bold; }
            .flow-text { font-size: 12px; font-style: italic; fill: #34495e;}
            .arrow-line { stroke-width: 2.5; }
            .success-flow { stroke: #2ecc71; }
            .internal-flow { stroke: #3498db; }
            .blocked-flow { stroke: #e74c3c; stroke-dasharray: 5 5; }
        </style>
        <marker id="arrowhead-success" markerWidth="10" markerHeight="7" refX="0" refY="3.5" orient="auto">
            <polygon points="0 0, 10 3.5, 0 7" fill="#2ecc71" />
        </marker>
        <marker id="arrowhead-internal" markerWidth="10" markerHeight="7" refX="0" refY="3.5" orient="auto">
            <polygon points="0 0, 10 3.5, 0 7" fill="#3498db" />
        </marker>
        <marker id="block-symbol" markerWidth="15" markerHeight="15" refX="7.5" refY="7.5" orient="auto">
            <line x1="2" y1="2" x2="13" y2="13" stroke="#e74c3c" stroke-width="3" stroke-linecap="round"/>
            <line x1="13" y1="2" x2="2" y2="13" stroke="#e74c3c" stroke-width="3" stroke-linecap="round"/>
        </marker>
    </defs>

    <!-- Fondo -->
    <rect width="100%" height="100%" fill="#f9f9f9"/>

    <!-- Actor Externo: Usuario -->
    <g transform="translate(40, 270)">
        <circle cx="25" cy="25" r="25" fill="#3498db"/>
        <circle cx="25" cy="20" r="8" fill="#ecf0f1"/>
        <path d="M10 48 C 15 35, 35 35, 40 48 Z" fill="#ecf0f1"/>
        <text x="7" y="70" class="user-text">Usuario</text>
    </g>

    <!-- Caja Principal: Máquina Host -->
    <rect x="150" y="30" width="630" height="540" class="host-box"/>
    <text x="170" y="60" class="host-text">Máquina Host (Ubuntu / Docker Engine)</text>

    <!-- Caja de Red: Red Docker Segura -->
    <rect x="170" y="90" width="590" height="420" class="network-box"/>
    <text x="190" y="120" class="network-text">Red Segura Docker (`fortaleza-net`)</text>

    <!-- Contenedores -->
    <g id="nginx">
        <rect x="200" y="230" width="160" height="140" class="container-box proxy-box"/>
        <text x="215" y="260" class="container-text">Gateway (Nginx)</text>
        <text x="210" y="285" class="label-text">Proxy Inverso</text>
        <text x="245" y="330" class="port-text">Puerto: 80</text>
    </g>
    <g id="app">
        <rect x="410" y="230" width="160" height="140" class="container-box app-box"/>
        <text x="415" y="260" class="container-text">Aplicación (Java)</text>
        <text x="420" y="285" class="label-text">Lógica de Negocio</text>
        <text x="455" y="330" class="port-text">Puerto: 8080</text>
    </g>
    <g id="db">
        <rect x="620" y="230" width="160" height="140" class="container-box db-box"/>
        <text x="630" y="260" class="container-text">Base de Datos</text>
        <text x="635" y="285" class="label-text">(PostgreSQL)</text>
        <text x="665" y="330" class="port-text">Puerto: 5432</text>
    </g>

    <!-- Flujos de Tráfico -->
    <!-- 1. User -> Nginx -->
    <path id="flow1" d="M90,295 C 140,295 160,295 200,295" fill="none" class="arrow-line success-flow" marker-end="url(#arrowhead-success)"/>
    <text class="flow-text" dy="-5"><textPath href="#flow1" startOffset="50%" text-anchor="middle">1. Petición HTTP</textPath></text>

    <!-- 2. Nginx -> App -->
    <path id="flow2" d="M360,295 C 380,295 390,295 410,295" fill="none" class="arrow-line internal-flow" marker-end="url(#arrowhead-internal)"/>
    <text class="flow-text" dy="-5"><textPath href="#flow2" startOffset="50%" text-anchor="middle">2. Proxy Pass</textPath></text>

    <!-- 3. App -> DB -->
    <path id="flow3" d="M570,295 C 590,295 600,295 620,295" fill="none" class="arrow-line internal-flow" marker-end="url(#arrowhead-internal)"/>
    <text class="flow-text" dy="-5"><textPath href="#flow3" startOffset="50%" text-anchor="middle">3. Conexión JDBC</textPath></text>

    <!-- 4. Blocked Flow -->
    <path id="flow4" d="M90,320 C 200,400 350,420 410,350" fill="none" class="arrow-line blocked-flow"/>
    <use href="#block-symbol" x="402" y="342"/>
    <text class="flow-text" fill="#c0392b" x="230" y="430">4. Acceso Directo a la App (BLOQUEADO)</text>

    <!-- Leyenda -->
    <g transform="translate(180, 530)">
        <rect x="0" y="0" width="10" height="10" fill="#2ecc71" rx="2"/>
        <text x="20" y="10" class="label-text">Tráfico Público Permitido</text>
        <rect x="220" y="0" width="10" height="10" fill="#3498db" rx="2"/>
        <text x="240" y="10" class="label-text">Tráfico Interno Permitido</text>
        <rect x="450" y="0" width="10" height="10" fill="#e74c3c" rx="2"/>
        <text x="470" y="10" class="label-text">Tráfico Bloqueado</text>
    </g>
</svg>
