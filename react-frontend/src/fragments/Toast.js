import React from 'react';

function Toast({color, message}) {
    return (
        <div id="liveToast" className={`toast align-items-center text-bg-${color} border-0`} role="alert" aria-live="assertive"
             aria-atomic="true">
            <div className="d-flex">
                <div className="toast-body">
                    {message}
                </div>
                <button type="button" className="btn-close btn-close-white me-2 m-auto" data-bs-dismiss="toast"
                        aria-label="Close"></button>
            </div>
        </div>
    );
}

export default Toast;